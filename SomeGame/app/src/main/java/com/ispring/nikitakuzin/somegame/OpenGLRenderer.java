package com.ispring.nikitakuzin.somegame;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.ispring.nikitakuzin.somegame.Utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_TEST;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnable;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by nikita.kuzin on 11/14/16.
 */

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private final static int POSITION_COUNT = 3;

    private Context m_context;
    private int m_programId;
    private FloatBuffer m_vertexData;
    private int uColorLocation;
    private int aPositionLocation;
    private int uMatrixLocation;

    private float[] mProjectionMatrix = new float[16];

    public OpenGLRenderer(Context context) {
        m_context = context;
        prepareData();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glEnable(GL_DEPTH_TEST);
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
        bindMatrix(width, height);
    }

    private void prepareData() {
        float z1 = -1.0f, z2 = -1.0f;

        float[] vertices = {
                -0.7f, -0.5f, z1,
                0.3f, -0.5f, z1,
                -0.2f, 0.3f, z1,

                // второй треугольник
                -0.3f, -0.4f, z2,
                0.7f, -0.4f, z2,
                0.2f, 0.4f, z2,
        };
        m_vertexData = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        m_vertexData.put(vertices);
    }

    private void bindData() {

        aPositionLocation = glGetAttribLocation(m_programId, "a_Position");
        m_vertexData.position(0);
        glVertexAttribPointer(aPositionLocation, POSITION_COUNT, GL_FLOAT, false, 0, m_vertexData);
        glEnableVertexAttribArray(aPositionLocation);
        uMatrixLocation = glGetUniformLocation(m_programId, "u_Matrix");
//        uColorLocation = glGetUniformLocation(m_programId, "u_Color");
//        m_vertexData.position(2);
//        glVertexAttribPointer(uColorLocation, 3, GL_FLOAT, false, 20, m_vertexData);
//        glEnableVertexAttribArray(uColorLocation);
    }

    private void bindMatrix(int width, int height) {
        float ratio;
        float left = -1.0f;
        float right = 1.0f;
        float bottom = -1.0f;
        float top = 1.0f;
        float near = 1.0f;
        float far = 8.0f;
        if (width > height) {
            ratio = (float) width / height;
            left *= ratio;
            right *= ratio;
        } else {
            ratio = (float) height / width;
            bottom *= ratio;
            top *= ratio;
        }

        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
        glUniformMatrix4fv(uMatrixLocation, 1, false, mProjectionMatrix, 0);
    }
    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        glLineWidth(5);
//        glDrawArrays(GL_TRIANGLES, 0, 3);
        glUniform4f(uColorLocation, 0f, 1f, 0f, 1f);
        glDrawArrays(GL_TRIANGLES, 0, 3);

        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        glDrawArrays(GL_TRIANGLES, 3, 3);
//
//        // синие треугольники
//        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
//        glDrawArrays(GL_TRIANGLES, 0, 12);
//
//        // зеленая линия
//        glUniform4f(uColorLocation, 0.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 12, 2);
//
//        // желтая линия
//        glUniform4f(uColorLocation, 1.0f, 1.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_LINES, 14, 2);
//
//        // красные точки
//        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
//        glDrawArrays(GL_POINTS, 16, 3);
    }
}
