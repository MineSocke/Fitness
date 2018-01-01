package com.example.enesakbulut.fitness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ListViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.enesakbulut.fitness.Adapter.CustomAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MusicActivityTwo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static int REQUEST_CODE = 40;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    String classname = this.getClass().getSimpleName();
    NavigationView navigationView;

    boolean localadded = false;
    int position;
    String pos;

    ArrayList<ListData> data = new ArrayList<>();
    FloatingActionButton ivExplore;
    ListView lvMusic;
    CustomAdapter customAdapter;
    ListData listData;
    SharedPreferences sharedPos;
    int workoutid;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_two);

        workoutid = getIntent().getIntExtra("workoutid", 0);

        if(workoutid == 0){
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            navigationView = (NavigationView) findViewById(R.id.nav_view1);
            navigationView.setNavigationItemSelectedListener(this);
        }else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        loadArrayList();

        ivExplore = (FloatingActionButton) findViewById(R.id.ivExplore);
        lvMusic = (ListView) findViewById(R.id.lvMusic);

        listData = new ListData();

        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                View view1 = view;
                createPopupMenu(view1);
            }
        });

        customAdapter = new CustomAdapter(getApplicationContext(), data);
        lvMusic.setAdapter(customAdapter);

        sharedPos = getSharedPreferences("position", 0);


        try{
            pos = sharedPos.getString("pos", "");
        }catch (NullPointerException e){
            Log.e("IOe", e.getMessage());
        }



        openExplorer();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(workoutid == 0){
            if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void openExplorer() {
        ivExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("audio/*");
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            Uri uri = data.getData();
            getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            dumpMetaData(uri);
            saveArrayList();
        }
    }

    public void dumpMetaData(Uri uri) {
        ListData listData = new ListData();
        Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                String displayName = cursor.getString(
                        cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                Log.i("DisplayName", "Display Name: " + displayName);

                int localpos;
                pos = sharedPos.getString("pos", "");

                try {
                    localpos = Integer.valueOf(pos);
                } catch (NumberFormatException e) {
                    localpos = 0;
                    Log.e("NumberFormatError: ", e.getMessage());
                } catch (NullPointerException e) {
                    localpos = 0;
                    Log.e("NullPointerException: ", e.getMessage());
                }


                listData.setName(displayName);
                listData.setUri(uri.toString());

                if (localpos > 0) {
                    localadded = false;

                    for (int i = 0; i < localpos; i++) {

                        if (data.get(i).getName().contains(listData.getName())) {
                            Toast.makeText(this, "Already added", Toast.LENGTH_SHORT).show();
                            localadded = true;

                        }
                    }
                    if (!localadded) {
                        Log.e("Localadded+IgetRan: ", localadded + "");
                        SharedPreferences.Editor editor = sharedPos.edit();
                        editor.putString("pos", (localpos + 1) + "");
                        editor.apply();

                        listData.setNumber(sharedPos.getString("pos", ""));

                        data.add(listData);
                        customAdapter.notifyDataSetChanged();
                        lvMusic.setAdapter(customAdapter);
                    }

                } else {
                    SharedPreferences.Editor editor = sharedPos.edit();
                    editor.putString("pos", (localpos + 1) + "");
                    editor.apply();

                    listData.setNumber(sharedPos.getString("pos", ""));

                    data.add(listData);
                    customAdapter.notifyDataSetChanged();
                    lvMusic.setAdapter(customAdapter);
                }
            }
        } finally {
            cursor.close();
        }
    }

    public void createPopupMenu(View view){
        if(customAdapter.getCount()>0){
            PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view,  Gravity.RIGHT);
            popupMenu.inflate(R.menu.items);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    int selectedId = menuItem.getItemId();
                    switch (selectedId){
                        case R.id.one:

                            SharedPreferences.Editor editor = sharedPos.edit();
                            pos = sharedPos.getString("pos", "");
                            int localpos;
                            localpos = Integer.valueOf(pos);
                            localpos = localpos-1;

                            editor.clear();
                            editor.putString("pos", String.valueOf(localpos));
                            editor.apply();

                            data.remove(position);

                            ArrayList<ListData> tempList = new ArrayList<>();
                            for(int i = 0; i<data.size();i++){
                                    String name = data.get(i).getName();
                                    Log.e("name: ", name);
                                    String number = String.valueOf(i);
                                    Log.e("number: ", number);
                                    String uri = data.get(i).getUri();
                                    Log.e("uri: ", uri);
                                    ListData listData = new ListData();
                                    listData.setName(name);
                                    listData.setNumber(number);
                                    listData.setUri(uri);
                                    tempList.add(listData);

                                }
                            data.clear();
                            for(int i=0; i<tempList.size(); i++){
                                    String name = tempList.get(i).getName();
                                    Log.e("getName: ", name);
                                    String number = String.valueOf(i+1);
                                    Log.e("getNumber: ", number);
                                    String uri = tempList.get(i).getUri();
                                    Log.e("getUri: ", uri);
                                    ListData listData = new ListData();
                                    listData.setName(name);
                                    listData.setNumber(number);
                                    listData.setUri(uri);
                                    data.add(listData);
                            }
                            customAdapter.notifyDataSetChanged();
                            lvMusic.setAdapter(customAdapter);
                            saveArrayList();



                            break;

                        case R.id.two:
                            System.out.println("Two");
                    }
                return true;
                }
            });
            popupMenu.show();
        }
    }

    public void saveArrayList(){
        SharedPreferences sharedArrayList = getSharedPreferences("sharedArrayList", 0);
        SharedPreferences.Editor editor = sharedArrayList.edit();
        editor.clear();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        editor.putString("jsonList", json);
        editor.apply();
    }

    public void loadArrayList(){
        SharedPreferences sharedArrayList = getSharedPreferences("sharedArrayList", 0 );
        Gson gson = new Gson();
        String json = sharedArrayList.getString("jsonList", null);
        Type type = new TypeToken<ArrayList<ListData>>(){}.getType();
        data = gson.fromJson(json, type);

        if(data==null){
            data = new ArrayList<>();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.nav_workout){
            Toast.makeText(this, "This is Workout", Toast.LENGTH_SHORT).show();
            if(!classname.equals(WorkoutList.class.getSimpleName())){
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, WorkoutList.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if (id == R.id.nav_progress){
            Toast.makeText(this, "This is progress", Toast.LENGTH_SHORT).show();
            if(!classname.equals(WorkoutList.class.getSimpleName())){
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, MusicActivityTwo.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if(id == R.id.nav_music_playlist){
            Toast.makeText(this, "This is Music-Playlist", Toast.LENGTH_SHORT).show();
            if(!classname.equals(MusicActivityTwo.class.getSimpleName())) {
                drawerLayout.closeDrawers();
                Intent intent = new Intent(this, MusicActivityTwo.class);
                startActivity(intent);
            }else {
                drawerLayout.closeDrawers();
            }


        }else if(id == R.id.nav_settings){
            Toast.makeText(this, "This is Settings", Toast.LENGTH_SHORT).show();


        }else if(id == R.id.nav_bug){
            Toast.makeText(this, "This is FoundABug?", Toast.LENGTH_SHORT).show();


        }else if(id == R.id.nav_rating){
            Toast.makeText(this, "This is Rating", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
}
