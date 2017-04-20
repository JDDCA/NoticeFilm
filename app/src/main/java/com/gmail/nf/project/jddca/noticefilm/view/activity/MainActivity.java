package com.gmail.nf.project.jddca.noticefilm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseUtils;
import com.gmail.nf.project.jddca.noticefilm.view.base.SingleFragmentActivity;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.GenerateFragment;

import lombok.experimental.PackagePrivate;


/**
 * Created by Yaroslav Lutsenko on 12.04.2017.
 * Главное Activity всего приложения
 */

@PackagePrivate
public class MainActivity extends SingleFragmentActivity{

    public final String TAG = getClass().getSimpleName();

//        transaction.replace(R.id.fragment_container, movieFragment);
//        transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        transaction.commit();


    /**Метод устанавливает начальный фрагмент {@link GenerateFragment}*/
    @Override
    protected Fragment createFragment() {
        return new GenerateFragment();
    }

    /**@see SingleFragmentActivity#createButtonNavigationFragment()*/
    @Override
    protected boolean createButtonNavigationFragment() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // для дополнительной проверки авторизации пользователя?
        checkSession();
    }

    /**Метод для проверки авторизации пользователя.*/
    private void checkSession() {
        if (!FirebaseUtils.checkSession()) {
            startActivity(LoginActivity.createIntent(this));
            finish();
        }
    }


    public static Intent createIntent(@NonNull Activity activity) {
        return new Intent(activity, MainActivity.class);
    }


}
