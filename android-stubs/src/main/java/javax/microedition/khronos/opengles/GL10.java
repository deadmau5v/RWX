package javax.microedition.khronos.opengles;

import java.nio.Buffer;

public interface GL10 {
    void glBindTexture(int target, int texture);
    void glBlendFunc(int sfactor, int dfactor);
    void glClear(int mask);
    void glClearColor(float red, float green, float blue, float alpha);
    void glColor4x(int red, int green, int blue, int alpha);
    void glColorPointer(int size, int type, int stride, Buffer pointer);
    void glDisable(int cap);
    void glDisableClientState(int array);
    void glDrawElements(int mode, int count, int type, Buffer indices);
    void glEnable(int cap);
    void glEnableClientState(int array);
    void glHint(int target, int mode);
    void glLoadIdentity();
    void glMatrixMode(int mode);
    void glOrthof(float left, int right, float bottom, int top, float zNear, float zFar);
    void glPopMatrix();
    void glPushMatrix();
    void glShadeModel(int mode);
    void glTexCoordPointer(int size, int type, int stride, Buffer pointer);
    void glTranslatef(float x, float y, float z);
    void glVertexPointer(int size, int type, int stride, Buffer pointer);
    void glViewport(int x, int y, int width, int height);

    int GL_TEXTURE_2D = 0x0DE1;
    int GL_BLEND = 0x0BE2;
    int GL_SRC_ALPHA = 0x0302;
    int GL_ONE_MINUS_SRC_ALPHA = 0x0303;
    int GL_COLOR_BUFFER_BIT = 0x4000;
    int GL_MODELVIEW = 0x1700;
    int GL_PROJECTION = 0x1701;
    int GL_VERTEX_ARRAY = 0x8074;
    int GL_TEXTURE_COORD_ARRAY = 0x8078;
    int GL_COLOR_ARRAY = 0x8076;
    int GL_FLOAT = 0x1406;
    int GL_UNSIGNED_SHORT = 0x1403;
    int GL_TRIANGLES = 0x0004;
    int GL_SMOOTH = 0x1D01;
    int GL_PERSPECTIVE_CORRECTION_HINT = 0x0C50;
    int GL_NICEST = 0x1102;
}
