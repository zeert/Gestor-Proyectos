package cl.udla.gestor;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by reyz on 04-05-14.
 */
public class ProjectFragment extends Fragment {

    public ProjectFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.nuevo_proyecto, container, false);

        return rootView;
    }


}
