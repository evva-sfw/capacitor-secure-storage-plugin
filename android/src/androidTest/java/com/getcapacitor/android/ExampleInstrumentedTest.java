package com.getcapacitor.android;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.evva.capacitor_secure_storage.SecureStoragePlugin;
import com.getcapacitor.JSArray;
import com.getcapacitor.JSObject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

  @Test
  public void useAppContext() throws Exception {
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    assertEquals(
      "com.evva.capacitor_secure_storage.test",
      appContext.getPackageName()
    );
  }

  @Test
  public void setTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    JSObject result = plugin._set("test", "test value");
    assertTrue(result.getBoolean("value"));
  }

  @Test
  public void getTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    plugin._set("test", "test value");
    JSObject result = plugin._get("test");
    assertEquals("test value", result.getString("value"));
  }

  @Test
  public void keysTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    JSObject result = plugin._set("test", "test value");
    assertTrue(result.getBoolean("value"));
    result = plugin._set("test2", "test value");
    assertTrue(result.getBoolean("value"));

    result = plugin._keys();
    JSArray keys = (JSArray) result.get("value");
    assertEquals(2, keys.length());
    assertTrue(keys.toList().contains("test"));
    assertTrue(keys.toList().contains("test2"));
  }

  @Test(expected = Exception.class)
  public void getNonExistingKeyTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    plugin._get("testNonExisting");
  }

  @Test(expected = Exception.class)
  public void removeTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);
    plugin._set("test", "test value");
    plugin._get("test");

    JSObject result = plugin._remove("test");
    assertTrue(result.getBoolean("value"));
    plugin._get("test");
  }

  @Test(expected = Exception.class)
  public void clearTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    plugin._set("test", "test value");
    plugin._set("test2", "test value");
    plugin._clear();
    plugin._get("test");
  }

  @Test(expected = Exception.class)
  public void clearTest2() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    plugin._set("test", "test value");
    plugin._set("test2", "test value");
    plugin._clear();
    plugin._get("test2");
  }

  @Test
  public void getPlatformTest() throws Exception {
    SecureStoragePlugin plugin = new SecureStoragePlugin();
    Context appContext = InstrumentationRegistry.getInstrumentation()
      .getTargetContext();
    plugin.loadTextContext(appContext);

    JSObject result = plugin._getPlatform();
    assertEquals("android", result.getString("value"));
  }
}
