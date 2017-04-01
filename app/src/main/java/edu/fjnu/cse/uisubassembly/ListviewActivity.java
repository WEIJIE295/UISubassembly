package edu.fjnu.cse.uisubassembly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListviewActivity extends AppCompatActivity {

    private String[] count = new String[]{"one","two","three","four","five"};
    private int image = R.drawable.uuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        List listItems = new ArrayList();
        for (int i=0;i<count.length;i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("header",image);
            listItem.put("text",count[i]);
            listItems.add(listItem);

        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.listview,new String[]{"header","text"},
                new int[]{R.id.imageView1,R.id.textView1});
        final ListView listView = (ListView) findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (listView.getCheckedItemCount()==0)
                    mode.setSubtitle("check some items");
                else if (listView.getCheckedItemCount()==1)
                    mode.setSubtitle("1 selected");
                else
                    mode.setSubtitle(listView.getCheckedItemCount() + "  selected");

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.list_context,menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
               return false;

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }
}
