package domain.fake.tictactoe;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private boolean isAIChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem checkable = menu.findItem(R.id.checkable_ai);
        MainActivityFragment fragment = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        isAIChecked = fragment.isAI;
        checkable.setChecked(isAIChecked);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        MainActivityFragment fragment = (MainActivityFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            fragment.resetBoard();
            return true;
        }
        else if (id == R.id.checkable_ai) {
            isAIChecked = !item.isChecked();
            item.setChecked(isAIChecked);
            fragment.setAI(isAIChecked);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
