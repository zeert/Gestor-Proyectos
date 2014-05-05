package cl.udla.gestor;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cl.udla.gestor.R;

/**
 * Created by reyz on 04-05-14.
 */
public class UsersFragment extends Fragment {

    public UsersFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.users, container, false);

        return rootView;
    }


}
