package allenwang.twitterclient.viewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import allenwang.twitterclient.ComposeActivity;
import allenwang.twitterclient.R;

public class ViewPagerActivity extends AppCompatActivity {
    static final int POST_REQUEST_CODE = 100;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyFragmentPagerAdapter pagerAdapter =
                new MyFragmentPagerAdapter(getSupportFragmentManager(), ViewPagerActivity.this);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        // Iterate over all tabs and set the custom view
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //tab.setCustomView(pagerAdapter.getTabView(i));
//        }
        setToolBarItem();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POST_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) { return; }
            //adapter.updateData(tweets);
            //adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void setToolBarItem() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.tw__ic_logo_default);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tweet:
                    Intent i = new Intent();
                    i.setClass(ViewPagerActivity.this, ComposeActivity.class);
                    startActivityForResult(i, POST_REQUEST_CODE);
                    break;
            }
            return true;
        }
    };
}
