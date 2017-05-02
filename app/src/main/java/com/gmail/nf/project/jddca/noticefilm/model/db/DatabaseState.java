package com.gmail.nf.project.jddca.noticefilm.model.db;


public interface DatabaseState {

    interface ResultFromDatabase {
        void addResult(boolean b);
    }

    interface ErrorFromDatabase {
        void addError (Throwable throwable);
    }
}
