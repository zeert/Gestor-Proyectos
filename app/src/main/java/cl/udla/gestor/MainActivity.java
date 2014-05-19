package cl.udla.gestor;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import cl.udla.gestor.R;

public class MainActivity extends Activity {
	    private String[] titulos;
	    private DrawerLayout NavDrawerLayout;
	    private ListView NavList;
        private ArrayList<Item_objct> NavItms;
        private TypedArray NavIcons;
	    private ActionBarDrawerToggle mDrawerToggle;
	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    NavigationAdapter NavAdapter;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);

			//Drawer Layout
			NavDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			//Lista
	        NavList = (ListView) findViewById(R.id.lista);
	        //Declaramos el header el cual sera el layout de header.xml
	        View header = getLayoutInflater().inflate(R.layout.header, null);
	        //Establecemos header
	        NavList.addHeaderView(header);
			//Tomamos listado  de imgs desde drawable
	        NavIcons = getResources().obtainTypedArray(R.array.navigation_iconos);			
			//Tomamos listado  de titulos desde el string-array de los recursos @string/nav_options
	        titulos = getResources().getStringArray(R.array.nav_options);
	        //Listado de titulos de barra de navegacion
	        NavItms = new ArrayList<Item_objct>();
	        //Agregamos objetos Item_objct al array
	        //Perfil	      
	        NavItms.add(new Item_objct(titulos[0], NavIcons.getResourceId(0, -1)));
	        //Usuarios
	        NavItms.add(new Item_objct(titulos[1], NavIcons.getResourceId(1, -1)));
	        //Editar Usuarios
	        NavItms.add(new Item_objct(titulos[2], NavIcons.getResourceId(2, -1)));
	        //Crear Proyecto
	        NavItms.add(new Item_objct(titulos[3], NavIcons.getResourceId(3, -1)));
	        //Crear Proveedores
	        NavItms.add(new Item_objct(titulos[4], NavIcons.getResourceId(4, -1)));
	        //Crear Tareas
	      /*  NavItms.add(new Item_objct(titulos[5], NavIcons.getResourceId(5, -1)));
	        //Reportes
	        NavItms.add(new Item_objct(titulos[6], NavIcons.getResourceId(6, -1)));*/
	      
	        //Declaramos y seteamos nuestro adaptador al cual le pasamos el array con los titulos	       
	        NavAdapter= new NavigationAdapter(this,NavItms);
	        NavList.setAdapter(NavAdapter);	
	        //Siempre vamos a mostrar el mismo titulo
	        mTitle = mDrawerTitle = getTitle();
	        
	        //Declaramos el mDrawerToggle y las imgs a utilizar
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                NavDrawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* Icono de navegacion*/
	                R.string.app_name,  /* "open drawer" description */
	                R.string.hello_world  /* "close drawer" description */
	                ) {

	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	            	Log.e("Cerrado completo", "!!");
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                Log.e("Apertura completa", "!!");
	            }
	        };	        
	        
	        // Establecemos que mDrawerToggle declarado anteriormente sea el DrawerListener
	        NavDrawerLayout.setDrawerListener(mDrawerToggle);
	        //Establecemos que el ActionBar muestre el Boton Home
	        getActionBar().setDisplayHomeAsUpEnabled(true);

	        //Establecemos la accion al clickear sobre cualquier item del menu.
	        //De la misma forma que hariamos en una app comun con un listview.
	        NavList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
	            	MostrarFragment(position);
	            }
	        });


	        //Cuando la aplicacion cargue por defecto mostrar la opcion Home
	        MostrarFragment(1);
	}
	
	/*Pasando la posicion de la opcion en el menu nos mostrara el Fragment correspondiente*/
    private void MostrarFragment(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
        case 1:
            fragment = new HomeFragment();
            break;
        case 2:
            fragment = new NewProveedor();
            break;
        case 3:
            fragment = new EditUsersFragment();
            break;
        case 4:
                fragment = new ProjectFragment();
                break;
        case 5:
            fragment = new TaskFragment();
            break;
        case 6:
            fragment = new ProvidersFragment();
                break;
           /* case 7:

                break;*/
            /*case 8:
                fragment = new ReportFragment();
                break;
*/
     
 
        default:
        	//si no esta la opcion mostrara un toast y nos mandara a Home
        	Toast.makeText(getApplicationContext(),"Opcion "+titulos[position-1]+" no disponible!", Toast.LENGTH_SHORT).show();
            fragment = new HomeFragment();
            position=1;
            break;
        }
        //Validamos si el fragment no es nulo
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
 
            // Actualizamos el contenido segun la opcion elegida
            NavList.setItemChecked(position, true);
            NavList.setSelection(position);
            //Cambiamos el titulo en donde decia "
            setTitle(titulos[position-1]);
            //Cerramos el menu deslizable
            NavDrawerLayout.closeDrawer(NavList);
        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.e("Error  ", "MostrarFragment "+position);
        }
    }
	  
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            Log.e("mDrawerToggle pushed", "x");
          return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    public void lanzarNuevoProveedor(View view){
        switch (view.getId()) {
            case R.id.btn_nuevo_proveedor:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("CREAR NUEVO PROVEEDOR");
                alertDialog.setMessage("Deberia Lanzar la Activity de Nuevo Proveedor");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {

                  }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();


        }
    }

    // Nuevo ROL

    public void newRol(View view){
        switch (view.getId()) {
            case R.id.crea_rol:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("CREAR NUEVO ROL");
                alertDialog.setMessage("Deberia Lanzar la Activity de Nuevo ROL");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();


        }
    }

    // Edita Usuario

    public void editUser(View view){
        switch (view.getId()) {
            case R.id.btn_edit_user:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("EDITAR NUEVO USUARIO");
                alertDialog.setMessage("Deberia Lanzar la Activity de EDITAR NUEVO USUARIO");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();


        }
    }


    // OK tarea


    public void okTarea(View view){
        switch (view.getId()) {
            case R.id.btn_ok_tarea:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("OK TAREA");
                alertDialog.setMessage("Deberia Lanzar la Activity de OK TAREA");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();


        }
    }

    // SUB TAREA

    public void subTarea(View view){
        switch (view.getId()) {
            case R.id.btn_sub_tarea:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("CREAR SUB TAREA");
                alertDialog.setMessage("Deberia Lanzar la Activity de CREAR SUB TAREA");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }

    // new TAREA

    public void newTarea(View view){
        switch (view.getId()) {
            case R.id.btn_new_tarea:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("CREAR NUEVA TAREA");
                alertDialog.setMessage("Deberia Lanzar la Activity de CREAR NUEVA TAREA");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }

    // SUB TAREA

    public void sigTarea(View view){
        switch (view.getId()) {
            case R.id.btn_sig_tarea:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("SIGUIENTE TAREA");
                alertDialog.setMessage("Deberia Lanzar la Activity de SIGUIENTE TAREA");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }

    // OK PROYECTO

    public void okProyecto(View view){
        switch (view.getId()) {
            case R.id.btn_ok_proyecto:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("OK PROYECTO");
                alertDialog.setMessage("Deberia Lanzar la Activity de OK PROYECTO");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }

    // SUB TAREA

    public void tareaProyecto(View view){
        switch (view.getId()) {
            case R.id.btn_tarea_proyecto:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("TAREAS POR PROYECTO");
                alertDialog.setMessage("Deberia Lanzar la Activity de CREACION DE TAREAS POR PROYECTO");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }

    // FORMA PROYECTO

    public void formaProyecto(View view){
        switch (view.getId()) {
            case R.id.btn_forma_proyecto:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("FORMA DE PROYECTO");
                alertDialog.setMessage("Deberia Lanzar la Activity de FORMA DE PROYECTO");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }

    // FORMA PROYECTO

    public void reporteProyecto(View view){
        switch (view.getId()) {
                case R.id.btn_reporte_proyecto:
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("REPORTE PROYECTO");
                alertDialog.setMessage("Deberia Lanzar la Activity de REPORTE PROYECTO");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.show();
        }
    }
}
