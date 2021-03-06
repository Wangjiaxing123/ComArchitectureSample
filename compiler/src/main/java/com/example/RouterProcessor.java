package com.example;


import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

public class RouterProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        UtilManager.getMgr().init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        UtilManager.getMgr().getMessager().printMessage(Diagnostic.Kind.NOTE,"process");

        Set<? extends Element>  elements=roundEnvironment.getElementsAnnotatedWith(Route.class);
        List<TargetInfo> targetInfos=new ArrayList<>();
        for (Element element:elements){
            if (!Utils.checkTypeValid(element)){
                continue;
            }

            TypeElement typeElement= (TypeElement) element;
            Route route =typeElement.getAnnotation(Route.class);
            targetInfos.add(new TargetInfo(typeElement,route.value()));
        }

        if (!targetInfos.isEmpty()){
            generateCode(targetInfos);
        }
        return false;
    }

    private void generateCode(List<TargetInfo> targetInfos){
        TypeElement activityType=UtilManager.getMgr().getElementUtils()
                .getTypeElement("android.app.Activity");

        ParameterizedTypeName actParam = ParameterizedTypeName.get(ClassName.get(Class.class),
                WildcardTypeName.subtypeOf(ClassName.get(activityType)));

        ParameterizedTypeName parma = ParameterizedTypeName.get(ClassName.get(Map.class),
                ClassName.get(String.class), actParam);

        ParameterSpec parameterSpec = ParameterSpec.builder(parma, "routers").build();

        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(Constants.ROUTE_METHOD_NAME)
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(parameterSpec);
        for (TargetInfo info : targetInfos) {
            methodSpecBuilder.addStatement("routers.put($S, $T.class)", info.getRoute(), info.getTypeElement());
        }

        TypeElement interfaceType = UtilManager
                .getMgr()
                .getElementUtils()
                .getTypeElement(Constants.ROUTE_INTERFACE_NAME);

        TypeSpec typeSpec = TypeSpec.classBuilder(Constants.ROUTE_CLASS_NAME)
                .addSuperinterface(ClassName.get(interfaceType))
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpecBuilder.build())
                .addJavadoc("Generated by Router. Do not edit it!\n")
                .build();
        try {
            JavaFile.builder(Constants.ROUTE_CLASS_PACKAGE, typeSpec)
                    .build()
                    .writeTo(UtilManager.getMgr().getFiler());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Route.class.getCanonicalName());
        return annotations;
    }

    /**
     * java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
