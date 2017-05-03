package com.gmail.nf.project.jddca.noticefilm.model.utils;


public class ExceptionService {

    public static class  NotAuthorizedException extends RuntimeException {
        public NotAuthorizedException() {
            super("User not authenticated");
        }
    }
}
