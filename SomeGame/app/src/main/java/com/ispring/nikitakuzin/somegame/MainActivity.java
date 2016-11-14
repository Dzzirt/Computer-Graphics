package com.ispring.nikitakuzin.somegame;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView m_glSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isGL2Supported()) {
            Toast.makeText(this, "OpenGL ES 2.0 is not supported", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        m_glSurfaceView = new GLSurfaceView(this);
        m_glSurfaceView.setEGLContextClientVersion(2);
        m_glSurfaceView.setRenderer(new OpenGLRenderer(this));
        setContentView(m_glSurfaceView);
    }

    private boolean isGL2Supported() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
        return (deviceConfigurationInfo.reqGlEsVersion >= 0x20000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        m_glSurfaceView.onPause();
    }
}
