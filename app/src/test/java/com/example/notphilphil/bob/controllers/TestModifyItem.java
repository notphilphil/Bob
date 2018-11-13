package com.example.notphilphil.bob.controllers;

import android.content.Context;
import android.content.Intent;
import android.test.mock.MockContext;
import android.widget.EditText;

import com.example.notphilphil.bob.models.Item;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestModifyItem {
    @InjectMocks
    private ModifyItemActivity obj;

    @Mock
    static EditText type;
    @Mock
    static EditText id;
    @Mock
    static EditText color;
    @Mock
    static EditText price;
    @Mock
    static EditText category;

    @Captor
    private ArgumentCaptor<String> typeCaptor;

    static EditText fakeType;
    static EditText fakeId;
    static EditText fakeColor;
    static EditText fakePrice;
    static EditText fakeCategory;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
//        Mockito.doCallRealMethod().when
    }

    @BeforeAll
    public static void beforeAll() {
        LoggedUser.setTesting(true);
//        obj = new ModifyItemActivity();
    }

    private void setEditTexts() {
        this.obj.type_et = new EditText(new MockContext());
        this.obj.id_et = new EditText(new MockContext());
        this.obj.color_et = new EditText(new MockContext());
        this.obj.price_et = new EditText(new MockContext());
        this.obj.category_et = new EditText(new MockContext());
    }

    @Test
    public void testPopulatePage() {

        this.obj = new ModifyItemActivity();
        Intent newIntent = new Intent();
        Item newItem = new Item("type", "id", "color", 0, "key", "category");

//        Mockito.when(this.obj.type_et.setText(""))
        obj.populatePage(newItem);
        Mockito.verify(obj).populatePage(newItem);

        newIntent.putExtra("item", newItem);
        this.setEditTexts();
        this.obj.populatePage(newItem);
        assertEquals(newItem.getType(), this.obj.type_et.getText().toString());
        assertEquals(newItem.getColor(), this.obj.color_et.getText().toString());
        assertEquals(newItem.getId(), this.obj.id_et.getText().toString());
        assertEquals(newItem.getPrice(), Double.parseDouble(this.obj.price_et.getText().toString()));
        assertEquals(newItem.getCategory(), this.obj.category_et.getText().toString());
    }

    @Test
    public void testOnSave() {
        this.obj = new ModifyItemActivity();
        Item newItem = new Item("type", "id", "color", 0, "key", "category");
        this.setEditTexts();
        this.obj.edit = true;
        this.obj.populatePage(newItem);
        Item retItem = this.obj.onSave(new Intent());
        assertEquals(retItem.getType(), newItem.getType());
        assertEquals(retItem.getId(), newItem.getId());
        assertEquals(retItem.getColor(), newItem.getColor());
        assertEquals(retItem.getPrice(), newItem.getPrice());
        assertEquals(retItem.getCategory(), newItem.getCategory());
    }
}
