package com.ispring.nikitakuzin.somegame;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.ispring.nikitakuzin.somegame.Utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINES;
import static android.opengl.GLES20.GL_LINE_LOOP;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glLineWidth;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by nikita.kuzin on 11/14/16.
 */

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private Context m_context;
    private int m_programId;
    private FloatBuffer m_vertexData;
    private int aColorLocation;
    private int aPositionLocation;


    public OpenGLRenderer(Context context) {
        m_context = context;
        prepareData();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 1);
        int vertexShaderId = ShaderUtils.createShader(m_context, GL_VERTEX_SHADER, R.raw.vertex_shader);
        int fragmentShaderId = ShaderUtils.createShader(m_context, GL_FRAGMENT_SHADER, R.raw.fragment_shader);
        m_programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId);
        glUseProgram(m_programId);
        bindData();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
    }

    private void prepareData() {
        float[] vertices = {
                -0.5f, -0.2f, 1.0f, 0.0f, 0.0f,
                0.0f, 0.2f, 0.0f, 1.0f, 0.0f,
                0.5f, -0.2f, 0.0f, 0.0f, 1.0f,
        };
        m_vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        m_vertexData.put(vertices);
    }

    private void bindData() {

        aPositionLocation = glGetAttribLocation(m_programId, "a_Position");
        m_vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, 2, GL_FLOAT, false, 20, m_vertexData);
        glEnableVertexAttribArray(aPositionLocation);

        aColorLocation = glGetAttribLocation(m_programId, "a_Color");
        m_vertexData.position(2);
        glVertexAttribPointer(aColorLocation, 3, GL_FLOAT, false, 20, m_vertexData);
        glEnableVertexAttribArray(aColorLocation);
//        glUniform4f(aColorLocation, 0f, 0f, 1f, 1f);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
        glLineWidth(5);
        glDrawArrays(GL_TRIANGLES, 0, 3);
//
//        // синие треугольники
//        glUniform4f(aColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
//        glDrawArrays(GL_TRIANGLES, 0, 12);
//
//        // зеленая линия
//        glUniform4f(aColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 12, 2);
//
//        // желтая линия
//        glUniform4f(aColorLocation, 1.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 14, 2);
//
//        // красные точки
//        glUniform4f(aColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_POINTS, 16, 3);
    }
}
