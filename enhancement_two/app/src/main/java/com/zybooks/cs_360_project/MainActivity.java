package com.zybooks.cs_360_project;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/* changes made to codebase

*** COMPLETED 11/19/25 *****
- Replaced the old TableView with a RecyclerView, which will make the project more scalable as more items are added.
- added a form at the top of the view for users to add new items to the list.
- Created a new item.xml layout file which defines the layout of each item as its added to the recyclerView
- created the Item.java class object that represents each inventory item, and provides getters and setter to be used in the ItemAdapter
- Created the ItemAdapter.java to bind the item data to the recycler view, and handle user interaction with each item.
- added comments to better understand the code when i return to it later
***

 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<Item> itemList = new ArrayList<>();
    private EditText editItemName, editItemQuantity;
    private Button addItemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);// this allows the content to appear underneath android system top bar
        setContentView(R.layout.activity_inventory);// sets starting layout view

        recyclerView = findViewById(R.id.itemsRecyclerView);//gets the recycler view from the layout file

        //create the adapter for the RecyclerView and passes the itemList and a lambda function for deletion functionality
        adapter = new ItemAdapter(itemList, item -> {
            itemList.remove(item);
            adapter.notifyDataSetChanged();
        });

        //set the adapter for the recyclerView
        recyclerView.setAdapter(adapter);
        //utilize a layout manager for displaying each item in the Recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //grab items from the layout
        editItemName = findViewById(R.id.editItemName);
        editItemQuantity = findViewById(R.id.editItemQuantity);
        addItemBtn = findViewById(R.id.addItemBtn);

        //created an onClickListener for the add items button
        addItemBtn.setOnClickListener(view -> {
            String name = editItemName.getText().toString();
            String quantityString = editItemQuantity.getText().toString();
            int quantity = Integer.parseInt(quantityString);

            Item newItem = new Item(name, quantity);
            itemList.add(newItem);

            adapter.notifyItemInserted(itemList.size() - 1);

            //I think its important to set the text in the field back to an empty string
            editItemName.setText("");
            editItemQuantity.setText("");
        });
    }
}