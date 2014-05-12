package cl.udla.gestor;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by reyz on 12-05-14.
 */

/*public class NewProveedor extends Fragment {
    @Override
    public onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.nuevo_proveedor);


    }
}
*/

public class NewProveedor extends Fragment {

    public NewProveedor(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.nuevo_proveedor, container, false);

        return rootView;
    }

}