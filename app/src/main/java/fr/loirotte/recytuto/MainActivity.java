package fr.loirotte.recytuto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TodoItem> items;
    private RecyclerView recycler;
    private LinearLayoutManager manager;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Log.i("INIT", "Fin initialisation composantes");

        // On récupère les items
        items = getItems();
        Log.i("INIT", "Fin initialisation items");

        // On initialise le RecyclerView
        recycler = (RecyclerView) findViewById(R.id.recycler);
        manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);

        adapter = new RecyclerAdapter(items);
        recycler.setAdapter(adapter);

        setRecyclerViewItemTouchListener();
        Log.i("INIT", "Fin initialisation recycler");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    ArrayList<TodoItem> getItems() {
        ArrayList<TodoItem> items = new ArrayList<TodoItem>();
        items.add(new TodoItem(TodoItem.Tags.Important, "Réviser ses cours"));
        items.add(new TodoItem(TodoItem.Tags.Normal, "Acheter du pain"));
        items.add(new TodoItem(TodoItem.Tags.Normal, "Marcher 30 mn par jour"));
        items.add(new TodoItem(TodoItem.Tags.Faible, "Manger 5 fruits et légumes"));
        items.add(new TodoItem(TodoItem.Tags.Normal, "Prendre des nouvelles des parents"));
        items.add(new TodoItem(TodoItem.Tags.Faible, "Préparer la prochaine soirée poker"));
        items.add(new TodoItem(TodoItem.Tags.Normal, "Révoir les premières saisons de Game of thrones"));
        items.add(new TodoItem(TodoItem.Tags.Faible, "Finir la vaisselle"));
        items.add(new TodoItem(TodoItem.Tags.Important, "Acheter un nouveau disque dur"));
        items.add(new TodoItem(TodoItem.Tags.Important, "Revoir les raccourcis clavier"));
        items.add(new TodoItem(TodoItem.Tags.Normal, "Tester une nouvelle techno"));
        items.add(new TodoItem(TodoItem.Tags.Faible, "Tester l'application en cours"));

        return items;
    }

    private void setRecyclerViewItemTouchListener() {
        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                // Non géré dans cet exemple (ce sont les drags) -> on retourne false
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                TodoItem item = items.get(position);

                switch(swipeDir) {
                    case ItemTouchHelper.RIGHT:
                        item.setDone(true);
                        break;
                    case ItemTouchHelper.LEFT:
                        item.setDone(false);
                        break;
                }
                recycler.getAdapter().notifyItemChanged(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recycler);
    }
}
