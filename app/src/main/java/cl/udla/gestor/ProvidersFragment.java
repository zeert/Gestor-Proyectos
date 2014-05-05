package cl.udla.gestor;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by reyz on 04-05-14.
 */
public class ProvidersFragment extends Fragment {

    public ProvidersFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.proveedor, container, false);

        return rootView;
    }


}
