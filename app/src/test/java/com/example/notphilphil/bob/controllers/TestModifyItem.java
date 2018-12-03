package com.example.notphilphil.bob.controllers;

import android.content.Intent;
import android.test.mock.MockContext;
import android.widget.EditText;

import com.example.notphilphil.bob.models.Item;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestModifyItem {
    @InjectMocks
    private ModifyItemActivity obj;

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
        this.obj.setType_et(new EditText(new MockContext()));
        this.obj.setId_et(new EditText(new MockContext()));
        this.obj.setColor_et(new EditText(new MockContext()));
        this.obj.setPrice_et(new EditText(new MockContext()));
        this.obj.setCategory_et(new EditText(new MockContext()));
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
        assertEquals(newItem.getType(), this.obj.getType_et().getText().toString());
        assertEquals(newItem.getColor(), this.obj.getColor_et().getText().toString());
        assertEquals(newItem.getId(), this.obj.getId_et().getText().toString());
        assertEquals(newItem.getPrice(), Double.parseDouble(this.obj.getPrice_et().getText().toString()));
        assertEquals(newItem.getCategory(), this.obj.getCategory_et().getText().toString());
    }

    @Test
    public void testOnSave() {
        this.obj = new ModifyItemActivity();
        Item newItem = new Item("type", "id", "color", 0, "key", "category");
        this.setEditTexts();
        this.obj.setEdit(true);
        this.obj.populatePage(newItem);
        Item retItem = this.obj.onSave(new Intent());
        assertEquals(retItem.getType(), newItem.getType());
        assertEquals(retItem.getId(), newItem.getId());
        assertEquals(retItem.getColor(), newItem.getColor());
        assertEquals(retItem.getPrice(), newItem.getPrice());
        assertEquals(retItem.getCategory(), newItem.getCategory());
    }
}
