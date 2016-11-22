package com.example.uripc.interfaceviewpagerex;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class ShowFireBase extends AppCompatActivity implements FireBaseLIstener {
    private Fragment[] fragments;
    Firebase ref;
    FireBaseLIstener fireBaseLIstener;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    User user;
    ArrayList<User> userList;
    MyListenerCons listenerCons;
    private AppBarLayout appBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fire_base);
        MyAdapter adapter = (new MyAdapter(getSupportFragmentManager()));
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
            userList = new ArrayList<>();
      /*  fireBaseLIstener=new FireBaseLIstener() {
            @Override
            public void onFirebaseDone() {
                Toast.makeText(ShowFireBase.this, "blablabla", Toast.LENGTH_SHORT).show();
                AppController.getInstance().setUser(user);
                ((ShowUserFrg) fragments[0]).setMyText();
                ((ShowPassFrg) fragments[1]).setMyText();
                ((ShowEmailFrg) fragments[2]).setMyText();
                fireBaseLIstener.onFirebaseDone();

            }
        };*/
        appBarLayout = (AppBarLayout)findViewById(R.id.appbarlay);
        tabLayout = (TabLayout)findViewById(R.id.tablay);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ref = new Firebase("https://radiant-inferno-2446.firebaseio.com/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.child("users").getChildren()){
                   user  = snapshot.getValue(User.class);
                    userList.add(user);
                    Log.d("userlists",userList.size()+"");
                    Log.d("email",userList.get(0).getEmail());
                    listenerCons = new MyListenerCons(ShowFireBase.this);
                    listenerCons.listenerMark();
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onFirebaseDone() {
        AppController.getInstance().setUser(userList.get(userList.size()-1));
        Toast.makeText(ShowFireBase.this, "made transaction", Toast.LENGTH_SHORT).show();

        ((ShowUserFrg) fragments[0]).setMyText();
        ((ShowPassFrg) fragments[1]).setMyText();
       ((ShowEmailFrg) fragments[2]).setMyText();

    }


    public class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
            fragments = new Fragment[3];
            fragments[0] = new ShowUserFrg();
            fragments[1] = new ShowPassFrg();
            fragments[2] = new ShowEmailFrg();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments[i];
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {

                return "user";
            } else if (position == 1) {
                return "pass";
            } else {
                return "email";
            }


        }

    }
}
