package com.example;

import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class UtilManager {
    public void setTypeUtils(Types typeUtils) {
        this.typeUtils = typeUtils;
    }

    public void setElementUtils(Elements elementUtils) {
        this.elementUtils = elementUtils;
    }

    public void setFiler(Filer filer) {
        this.filer = filer;
    }

    public void setMessager(Messager messager) {
        this.messager = messager;
    }

    public Types getTypeUtils() {
        return typeUtils;
    }

    public Elements getElementUtils() {
        return elementUtils;
    }

    public Filer getFiler() {
        return filer;
    }

    public Messager getMessager() {
        return messager;
    }



    private Types typeUtils;

    private Elements elementUtils;

    private Filer filer;

    private Messager messager;

    public void init(ProcessingEnvironment environment) {
        setTypeUtils(environment.getTypeUtils());
        setElementUtils(environment.getElementUtils());
        setFiler(environment.getFiler());
        setMessager(environment.getMessager());
    }

    private UtilManager() {
    }

    private static class UtilManagerHolder{
        private static final UtilManager INSTANCE=new UtilManager();
    }
    public static UtilManager getMgr(){
        return UtilManagerHolder.INSTANCE;
    }

}
