package com.gmail.nf.project.jddca.noticefilm.view.contract;

/** Интерфейс для отображения ошибок*/
public interface BaseContract extends ContextFragmentContract {

    void showError(Throwable throwable);
}
