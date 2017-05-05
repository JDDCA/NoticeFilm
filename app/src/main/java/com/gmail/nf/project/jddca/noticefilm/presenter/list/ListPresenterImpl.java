package com.gmail.nf.project.jddca.noticefilm.presenter.list;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseService;
import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.utils.ExceptionService;
import com.gmail.nf.project.jddca.noticefilm.model.utils.FirebaseAuthService;
import com.gmail.nf.project.jddca.noticefilm.presenter.base.BasePresenterImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragmentImpl;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.list.ListFragment;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.list.ListFragmentImpl;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ListPresenterImpl extends BasePresenterImpl implements ListPresenter {

    private ListFragment fragment;
    private DatabaseService databaseService;


    public ListPresenterImpl(ListFragment fragment) {
        this.fragment = fragment;
        databaseService = new DatabaseServiceImpl();
    }

    @Override
    public void onCreate() {
            databaseService.getRefList().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getChildrenCount()<1)
                        showInfo();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    fragment.showError(databaseError.toException());
                }
            });
    }

    private void showInfo() {
        fragment.getInfoPage().setVisibility(View.VISIBLE);
        fragment.getBodyPage().setVisibility(View.GONE);
    }

    @Override
    public void onStop() {

    }

    @Override
    public DatabaseReference getRefList() {
        return databaseService.getRefList();
    }

    @Override
    public void removeListsMovie(Film film) {
        databaseService.removeListsMovie(film);
        getRefList().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()<1)
                    showInfo();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                fragment.showError(databaseError.toException());
            }
        });
    }

    @Override
    public void transactionToGenerate() {
        if (!FirebaseAuthService.isAnonymousUser()){
            FragmentManager fragmentManager = fragment.getFrgmnt().getFragmentManager();
            Fragment fragmentByTag = fragmentManager.findFragmentByTag(ListFragmentImpl.LIST_TAG);
            fragmentManager
                    .beginTransaction()
                    .remove(fragmentByTag)
                    .add(R.id.fragment_container, new GenerateFragmentImpl(), GenerateFragmentImpl.GENERATE_TAG)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }else
            fragment.showError(new ExceptionService.NotAuthorizedException());
    }
}
