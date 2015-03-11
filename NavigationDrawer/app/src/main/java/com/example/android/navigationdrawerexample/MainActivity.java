/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationdrawerexample;

import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mMenuItemTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mMenuItemTitles = getResources().getStringArray(R.array.menu_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuItemTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItemWelcome(0);//Sets start item
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        case R.id.action_settings:
            openAndroidSettings();
                return true;
        case R.id.action_browser:
             openAndroidBrowser();
                return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Opens Android device settings
     */
    public void openAndroidSettings() {
        startActivityForResult(new Intent(Settings.ACTION_SETTINGS), 0);
    }

    /**
     * Opens Android device browser
     */
    public void openAndroidBrowser() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch(position) {
                case 0:
                    selectItemWelcome(position);//Welcome
                    System.out.println("DrawerItemClickListener Welcome pos: " + position);
                    break;
                case 1:
                    selectItemDepartment(position);//Department
                    System.out.println("DrawerItemClickListener Department pos: " + position);
                    break;
                case 2:
                    selectItemAffairs(position);//Affairs
                    System.out.println("DrawerItemClickListener Affairs pos: " + position);
                    break;
                case 3:
                    selectItemProspective(position);//Prospective
                    System.out.println("DrawerItemClickListener Prospective pos: " + position);
                    break;
                case 4:
                    selectItemResearch(position);//Research
                    System.out.println("DrawerItemClickListener Research pos: " + position);
                    break;
                case 5:
                    selectItemNews(position);//News
                    System.out.println("DrawerItemClickListener News pos: " + position);
                    break;
                case 6:
                    selectItemEvents(position);//Events
                    System.out.println("DrawerItemClickListener Events pos: " + position);
                    break;
                case 7:
                    selectItemWelcome(position);//Contact
                    System.out.println("DrawerItemClickListener Contact pos: " + position);
                    break;
                case 8:
                    selectItemWelcome(position);//Nextitem
                    System.out.println("DrawerItemClickListener Nextitem pos: " + position);
                    break;
                default:
                    selectItemWelcome(position);
                    break;
            }
        }
    }

    private void selectItemWelcome(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putInt(WelcomeFragment.ARG_WELCOME_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setmDrawer(position);

    }

    private void selectItemDepartment(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new DepartmentFragment();
        Bundle args = new Bundle();
        args.putInt(DepartmentFragment.ARG_DEPARTMENT_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    private void selectItemAffairs(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new AffairsFragment();
        Bundle args = new Bundle();
        args.putInt(AffairsFragment.ARG_AFFAIRS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setmDrawer(position);

    }

    private void selectItemProspective(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ProspectiveFragment();
        Bundle args = new Bundle();
        args.putInt(ProspectiveFragment.ARG_PROSPECTIVE_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setmDrawer(position);

    }

    private void selectItemResearch(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new ResearchFragment();
        Bundle args = new Bundle();
        args.putInt(ResearchFragment.ARG_RESEARCH_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setmDrawer(position);

    }

    private void selectItemNews(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putInt(NewsFragment.ARG_NEWS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setmDrawer(position);

    }

    private void selectItemEvents(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putInt(EventsFragment.ARG_EVENTS_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setmDrawer(position);

    }

    public void setmDrawer(int position) {
        mDrawerList.setItemChecked(position, true);
        setTitle(mMenuItemTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a welcome fragment
     */
    public static class WelcomeFragment extends Fragment {
        public static final String ARG_WELCOME_NUMBER = "WELCOME_number";

        public WelcomeFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            int i = getArguments().getInt(ARG_WELCOME_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            //int drawable = getResources().getIdentifier(menuItem,"drawable",null);
            //System.out.println("drawable int: " + drawable);

            //((ImageView) rootView.findViewById(R.id.fragment_welcome)).setImageResource(drawable);
            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    public static class DepartmentFragment extends Fragment {
        public static final String ARG_DEPARTMENT_NUMBER = "DEPARTMENT_number";

        public DepartmentFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_department, container, false);
            int i = getArguments().getInt(ARG_DEPARTMENT_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    public static class AffairsFragment extends Fragment {
        public static final String ARG_AFFAIRS_NUMBER = "AFFAIRS_number";

        public AffairsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_affairs, container, false);
            int i = getArguments().getInt(ARG_AFFAIRS_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    public static class ProspectiveFragment extends Fragment {
        public static final String ARG_PROSPECTIVE_NUMBER = "PROSPECTIVE_number";

        public ProspectiveFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_prospective, container, false);
            int i = getArguments().getInt(ARG_PROSPECTIVE_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    public static class ResearchFragment extends Fragment {
        public static final String ARG_RESEARCH_NUMBER = "RESEARCH_number";

        public ResearchFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_research, container, false);
            int i = getArguments().getInt(ARG_RESEARCH_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

    public static class NewsFragment extends Fragment {
        public static final String ARG_NEWS_NUMBER = "NEWS_number";

        public NewsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_news, container, false);
            int i = getArguments().getInt(ARG_NEWS_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            getActivity().setTitle(menuItem);

            return rootView;
        }
    }


    public static class EventsFragment extends Fragment {
        public static final String ARG_EVENTS_NUMBER = "EVENTS_number";

        public EventsFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_events, container, false);
            int i = getArguments().getInt(ARG_EVENTS_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("Fragment: " + menuItem + ", i: " + i);

            getActivity().setTitle(menuItem);

            return rootView;
        }
    }

}

/*
    private void selectItemLikeWelcome(int position) {
        // update the main content by replacing fragments
        try {
            Fragment fragment = new WelcomeFragment();
            Bundle args = new Bundle();
            args.putInt(ImageTextFragment.ARG_MENU_NUMBER, position);
            fragment.setArguments(args);

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(mMenuItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } catch (Exception ex) {
            System.out.println("Exception ex:" + ex);
        }
    }
    */

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
    /*
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String menuItem = getResources().getStringArray(R.array.menu_items_array)[i];

            System.out.println("i: " + i + ", menuItem: " + menuItem);

            int imageId = getResources().getIdentifier(menuItem.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());

            System.out.println("imageId: " + imageId);

            ((ImageView) rootView.findViewById(R.id.image_planets_id)).setImageResource(imageId);
            getActivity().setTitle(menuItem);

            System.out.println("rootView: " + rootView.toString());

            return rootView;
        }
    }
    */