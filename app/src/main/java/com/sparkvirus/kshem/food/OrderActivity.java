package com.sparkvirus.kshem.food;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sparkvirus.kshem.food.R;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }
       @Override
    public void onOptionsMenuClosed(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);

        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.home_menu:
                finish();
                startActivity(new Intent(getApplication(), MenuActivity.class));
                
            default:
                return super.onOptionsItemSelected(item);
        }
        
    }
}
