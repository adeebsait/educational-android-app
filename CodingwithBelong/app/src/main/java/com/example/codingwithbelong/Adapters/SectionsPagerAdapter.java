package com.example.codingwithbelong.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.codingwithbelong.CourseDetailsActivity;
import com.example.codingwithbelong.Fragments.CppFragment;
import com.example.codingwithbelong.Fragments.CppTextFragment;
import com.example.codingwithbelong.Fragments.CppVideoFragment;
import com.example.codingwithbelong.Fragments.JavaFragment;
import com.example.codingwithbelong.Fragments.JavaTextFragment;
import com.example.codingwithbelong.Fragments.JavaVideoFragment;
import com.example.codingwithbelong.Fragments.PythonFragment;
import com.example.codingwithbelong.Fragments.PythonTextFragment;
import com.example.codingwithbelong.Fragments.PythonVideoFragment;
import com.example.codingwithbelong.Fragments.QuizFragment;
import com.example.codingwithbelong.Fragments.SettingsFragment;
import com.example.codingwithbelong.Fragments.TextFragment;
import com.example.codingwithbelong.Fragments.VideosFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
            Fragment fragment1 = null;
            if(CourseDetailsActivity.subject.contains("funda"))
            {
                fragment1 = new TextFragment();
            }else if(CourseDetailsActivity.subject.contains("cpp")){
                fragment1 = new CppTextFragment();
            }else if(CourseDetailsActivity.subject.contains("java")){
                fragment1 = new JavaTextFragment();
            }
            else if(CourseDetailsActivity.subject.contains("python")){
                fragment1 = new PythonTextFragment();
            }else
                fragment1 = new TextFragment();
            return fragment1;

            case 1:
            Fragment fragment2 = null;
            if(CourseDetailsActivity.subject.contains("funda"))
            {
                fragment2 = new VideosFragment();
            }else if(CourseDetailsActivity.subject.contains("cpp")){
                fragment2 = new CppVideoFragment();
            }else if(CourseDetailsActivity.subject.contains("java")){
                fragment2 = new JavaVideoFragment();
            }
            else if(CourseDetailsActivity.subject.contains("python")){
                fragment2 = new PythonVideoFragment();
            }else
                fragment2 = new VideosFragment();
            return fragment2;
            case 2:
                Fragment fragment = null;
                if(CourseDetailsActivity.subject.contains("funda"))
                {
                    fragment = new QuizFragment();
                }else if(CourseDetailsActivity.subject.contains("cpp")){
                    fragment = new CppFragment();
                }else if(CourseDetailsActivity.subject.contains("java")){
                    fragment = new JavaFragment();
                }
                else if(CourseDetailsActivity.subject.contains("python")){
                    fragment = new PythonFragment();
                }else
                    fragment = new QuizFragment();
                return fragment;


                default:
                    return null;

        }


    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0:

                return "Notes";
            case 1:


            return "Videos";
            case 2:

                return "Tests";



            default:
                return null;

        }


    }
}
