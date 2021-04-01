package com.mx.ebitware.adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PagerAdapterFragment extends FragmentPagerAdapter {
    private List<Class<? extends Fragment>> fragments;
    private List<Bundle> bundles;
    public List<String> titles;

    private FragmentManager mFragmentManager;

    public PagerAdapterFragment(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.fragments = new ArrayList<>();
        this.bundles = new ArrayList<>();
        this.titles = new ArrayList<>();
    }

    public void addFragment(Class<? extends Fragment> clazz) {
        this.fragments.add(clazz);
    }

    public void addFragment(Class<? extends Fragment> clazz, Bundle bundle) {
        this.fragments.add(clazz);
        this.bundles.add(bundle);
    }

    public void addFragment(Class<? extends Fragment> clazz, String title) {
        this.fragments.add(clazz);
        this.titles.add(title);
    }

    public void addFragment(Class<? extends Fragment> clazz, String title, Bundle bundle) {
        this.fragments.add(clazz);
        this.titles.add(title);
        this.bundles.add(bundle);
    }

    public void changeTitle(int position, String title){
        titles.set(position,title);
        notifyDataSetChanged();
    }

    public void removeFragment(int position){
        if(!this.fragments.isEmpty() &&  this.fragments.size() > position){
            this.fragments.remove(position);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titles.size() > position){
            return titles.get(position);
        }
        else {
            return super.getPageTitle(position);
        }
    }

    @Override
    public Fragment getItem(int position) {
        // TODO Auto-generated method stub
        Fragment fragment = null;
        try {
            Method m = this.fragments.get(position).getDeclaredMethod("newInstance");
            fragment = (Fragment) m.invoke(null);
            if(bundles.size() > position){
                fragment.setArguments(bundles.get(position));
            }
        }  catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return  fragment;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return this.fragments.size();
    }

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public void setFragmentManager(FragmentManager mFragmentManager) {
        this.mFragmentManager = mFragmentManager;
    }
}