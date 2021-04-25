package com.example.notphilphil.bob.controllers

import android.content.Intent
import android.test.mock.MockContext
import android.widget.EditText

import com.example.notphilphil.bob.models.Item

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeAll
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

import junit.framework.Assert.assertEquals

@RunWith(MockitoJUnitRunner::class)
class TestModifyItem {
    @InjectMocks
    private var obj: ModifyItemActivity? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        //        Mockito.doCallRealMethod().when
    }

    private fun setEditTexts() {
        this.obj!!.type_et = EditText(MockContext())
        this.obj!!.id_et = EditText(MockContext())
        this.obj!!.color_et = EditText(MockContext())
        this.obj!!.price_et = EditText(MockContext())
        this.obj!!.category_et = EditText(MockContext())
    }

    @Test
    fun testPopulatePage() {

        this.obj = ModifyItemActivity()
        val newIntent = Intent()
        val newItem = Item("type", "id", "color", 0.0, "key", "category")

        //        Mockito.when(this.obj.type_et.setText(""))
        obj!!.populatePage(newItem)
        Mockito.verify<ModifyItemActivity>(obj).populatePage(newItem)

        newIntent.putExtra("item", newItem)
        this.setEditTexts()
        this.obj!!.populatePage(newItem)
        assertEquals(newItem.type, this.obj!!.type_et.text.toString())
        assertEquals(newItem.color, this.obj!!.color_et.text.toString())
        assertEquals(newItem.id, this.obj!!.id_et.text.toString())
        assertEquals(newItem.price, java.lang.Double.parseDouble(this.obj!!.price_et.text.toString()))
        assertEquals(newItem.category, this.obj!!.category_et.text.toString())
    }

    @Test
    fun testOnSave() {
        this.obj = ModifyItemActivity()
        val newItem = Item("type", "id", "color", 0.0, "key", "category")
        this.setEditTexts()
        this.obj!!.edit = true
        this.obj!!.populatePage(newItem)
        val retItem = this.obj!!.onSave(Intent())
        assertEquals(retItem!!.type, newItem.type)
        assertEquals(retItem.id, newItem.id)
        assertEquals(retItem.color, newItem.color)
        assertEquals(retItem.price, newItem.price)
        assertEquals(retItem.category, newItem.category)
    }

    companion object {

        @BeforeAll
        fun beforeAll() {
            //LoggedUser.setTesting(true)
            //        obj = new ModifyItemActivity();
        }
    }
}
