package com.gmail.nf.project.jddca.noticefilm.model.injection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Yaroslav Lutsenko on 07.04.2017.
 * Name's scope annotation for Application layer
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
