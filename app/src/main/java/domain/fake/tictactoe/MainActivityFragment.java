package domain.fake.tictactoe;

import android.content.DialogInterface;
import android.graphics.drawable.LevelListDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v4.app.FragmentTransaction;

import java.util.logging.Level;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "MainActivityFragment";

    public MainActivityFragment() {
    }

    ImageButton btn[] = new ImageButton[9];
    int status[] = new int[9];
    int turn = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(

                R.layout.fragment_main, container, false);



        setAllListeners(view);

        retainBoard();




        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    public void retainBoard()
    {
        LevelListDrawable levelList;
        ImageView iv;
        for(int i=0; i<9; i++)
        {
            iv = btn[i];
            levelList = (LevelListDrawable)iv.getDrawable();
            levelList.setLevel(status[i]);
        }
    }

    public void setAllListeners(View view)
    {
        btn[0] = (ImageButton) view.findViewById(R.id.space00);
        btn[1] = (ImageButton) view.findViewById(R.id.space01);
        btn[2] = (ImageButton) view.findViewById(R.id.space02);
        btn[3] = (ImageButton) view.findViewById(R.id.space10);
        btn[4] = (ImageButton) view.findViewById(R.id.space11);
        btn[5] = (ImageButton) view.findViewById(R.id.space12);
        btn[6] = (ImageButton) view.findViewById(R.id.space20);
        btn[7] = (ImageButton) view.findViewById(R.id.space21);
        btn[8] = (ImageButton) view.findViewById(R.id.space22);
        for(int i=0; i<9; i++)
        {
            btn[i].setOnClickListener(this);
        }
    }

    public void changeSpace(ImageView v, int pos)
    {
        LevelListDrawable levelList=(LevelListDrawable)v.getDrawable();
        if(status[pos] == 0)
        {
            levelList.setLevel(turn);
            status[pos] = turn;
            changeTurn();
        }
    }

    private void changeTurn()
    {
        if(checkVictory()!=0)
            showVictory(checkVictory());
        if(turn==1)
            turn = 2;
        else
            turn = 1;
    }

    public void showVictory(int player)
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        if(player!=3)
        {
            builder1.setMessage("Player " + turn + " wins!");
        }
        else
        {
            builder1.setMessage(R.string.draw);
        }

        builder1.setCancelable(false);
        builder1.setPositiveButton(R.string.restart,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        resetBoard();


                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void resetBoard()
    {
        LevelListDrawable levelList;
        ImageView iv;
        for(int i=0; i<9; i++)
        {
            iv = btn[i];
            levelList = (LevelListDrawable)iv.getDrawable();
            levelList.setLevel(0);
            status[i] = 0;
        }
    }

    private int checkVictory()
    {
        int counter = 0;
        for(int i = 0; i<3; i++)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 3; i<6; i++)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 6; i<9; i++)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 0; i<7; i+=3)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 1; i<8; i+=3)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 2; i<9; i+=3)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 0; i<9; i+=4)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 2; i<7; i+=2)
        {
            if(status[i]==turn)
                counter++;
        }
        if(counter==3)
            return turn;
        counter = 0;
        for(int i = 0; i<9; i++)
        {
            if(status[i]!=0)
                counter++;
        }
        if(counter==9)
            return 3;
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.space00:
                changeSpace((ImageView) view, 0);
                break;
            case R.id.space01:
                changeSpace((ImageView) view, 1);
                break;
            case R.id.space02:
                changeSpace((ImageView) view, 2);
                break;
            case R.id.space10:
                changeSpace((ImageView) view, 3);
                break;
            case R.id.space11:
                changeSpace((ImageView) view, 4);
                break;
            case R.id.space12:
                changeSpace((ImageView) view, 5);
                break;
            case R.id.space20:
                changeSpace((ImageView) view, 6);
                break;
            case R.id.space21:
                changeSpace((ImageView) view, 7);
                break;
            case R.id.space22:
                changeSpace((ImageView) view, 8);
                break;
            default:
                Log.e(TAG , "Unknown: " + view.getId());
                break;
        }
    }
}
