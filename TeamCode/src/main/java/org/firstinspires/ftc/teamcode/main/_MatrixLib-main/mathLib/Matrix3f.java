package org.firstinspires.ftc.teamcode.main._MatrixLib-main.mathLib;

import java.io.IOException;

public class Matrix3f {

    private float[] m;

    public Matrix3f(){
        m = new float[]{1.0f, 0.0f, 0.0f,
                        0.0f, 1.0f, 0.0f,
                        0.0f, 0.0f, 1.0f};
    }

    public Matrix3f(float[] newMatrix) {
        if (newMatrix.length == 9){
            m = newMatrix;
        } else {
            IOException e = new IOException("Matrix Length is not 9!");
            e.printStackTrace();
        }
    }

    public Vector3f matMul(Vector3f a){
        float nx = a.getX() * this.m[0] + a.getY() * this.m[1] + a.getZ() * this.m[2];
        float ny = a.getX() * this.m[3] + a.getY() * this.m[4] + a.getZ() * this.m[5];
        float nz = a.getX() * this.m[6] + a.getY() * this.m[7] + a.getZ() * this.m[8];

        return new Vector3f(nx, ny, nz);
    }

    public static Matrix3f matMul(Matrix3f a, Matrix3f b){
        float[][] cols = new float[][] {b.getCol(0), b.getCol(1), b.getCol(2)};
        float[][] rows = new float[][] {a.getRow(0), a.getRow(1), a.getRow(2)};
        float[] output = new float[9];

        for (int i = 0; i < 3; i++) {
            output[0 + i * 3] = cols[0][0] * rows[i][0] + cols[0][1] * rows[i][1] + cols[0][2] * rows[i][2];
            output[1 + i * 3] = cols[1][0] * rows[i][0] + cols[1][1] * rows[i][1] + cols[1][2] * rows[i][2];
            output[2 + i * 3] = cols[2][0] * rows[i][0] + cols[2][1] * rows[i][1] + cols[2][2] * rows[i][2];
        }

        return new Matrix3f(output);
    }

    public void add(Matrix3f b){
        for (int i = 0; i < 9; i++){
            this.m[i] += b.m[i];
        }
    }

    public void mul(float b){
        for (int i = 0; i < 9; i++){
            this.m[i] *= b;
        }
    }

    public static Matrix3f add(Matrix3f a, Matrix3f b){
        float[] newMat = new float[9];
        for (int i = 0; i < 9; i++){
            newMat[i] = a.m[i] + b.m[i];
        }

        return new Matrix3f(newMat);
    }

    public static Matrix3f mul(Matrix3f a, float b){
        float[] newMat = new float[9];
        for (int i = 0; i < 9; i++){
            newMat[i] = a.m[i] * b;
        }

        return new Matrix3f(newMat);
    }

    public float det(){
        float a = (m[4] * m[8] - m[5] * m[7]) * m[0];
        float b = (m[3] * m[8] - m[5] * m[6]) * m[1];
        float c = (m[3] * m[7] - m[6] * m[4]) * m[2];

        return a - b + c;
    }

    public void transpose(){
        float[] row1 = new float[]{m[0], m[1], m[2]};
        float[] row2 = new float[]{m[3], m[4], m[5]};
        float[] row3 = new float[]{m[6], m[7], m[8]};

        this.m = new float[]{row1[0], row2[0], row3[0],
                             row1[1], row2[1], row3[1],
                             row1[2], row2[2], row3[2]};
    }

    public void invert(){
        float det_inv = 1 / this.det();

        float[] cofactor_mat = new float[] {
                this.getMinorDet(0), this.getMinorDet(3), this.getMinorDet(6),
                this.getMinorDet(1), this.getMinorDet(4), this.getMinorDet(7),
                this.getMinorDet(2), this.getMinorDet(5), this.getMinorDet(8),
        };

        cofactor_mat[1] *= -1;
        cofactor_mat[3] *= -1;
        cofactor_mat[5] *= -1;
        cofactor_mat[7] *= -1;

        for (int i = 0; i < 9; i++){
            cofactor_mat[i] *= det_inv;
        }

        this.m = cofactor_mat;
    }

    public float[] getRow(int idx){
        return new float[]{this.m[idx * 3], this.m[idx * 3 + 1], this.m[idx * 3 + 2]};
    }

    public float[] getCol(int idx){
        return new float[]{this.m[idx], this.m[idx + 3], this.m[idx + 6]};
    }

    public float getMinorDet(int element_idx){
        float[] minor_mat = new float[4];

        switch (element_idx) {
            case 0:
                minor_mat[0] = m[4];
                minor_mat[1] = m[5];
                minor_mat[2] = m[7];
                minor_mat[3] = m[8];
                break;
            case 1:
                minor_mat[0] = m[3];
                minor_mat[1] = m[5];
                minor_mat[2] = m[6];
                minor_mat[3] = m[8];
                break;
            case 2:
                minor_mat[0] = m[3];
                minor_mat[1] = m[4];
                minor_mat[2] = m[6];
                minor_mat[3] = m[7];
                break;
            case 3:
                minor_mat[0] = m[1];
                minor_mat[1] = m[2];
                minor_mat[2] = m[7];
                minor_mat[3] = m[8];
                break;
            case 4:
                minor_mat[0] = m[0];
                minor_mat[1] = m[2];
                minor_mat[2] = m[6];
                minor_mat[3] = m[8];
                break;
            case 5:
                minor_mat[0] = m[0];
                minor_mat[1] = m[1];
                minor_mat[2] = m[6];
                minor_mat[3] = m[7];
                break;
            case 6:
                minor_mat[0] = m[1];
                minor_mat[1] = m[2];
                minor_mat[2] = m[4];
                minor_mat[3] = m[5];
                break;
            case 7:
                minor_mat[0] = m[0];
                minor_mat[1] = m[2];
                minor_mat[2] = m[3];
                minor_mat[3] = m[5];
                break;
            case 8:
                minor_mat[0] = m[0];
                minor_mat[1] = m[1];
                minor_mat[2] = m[3];
                minor_mat[3] = m[4];
                break;
        }

        float det = minor_mat[0] * minor_mat[3] - minor_mat[1] * minor_mat[2];

        return det;
    }

    public Matrix3f getAsCofactor(){
        return new Matrix3f(new float[]{m[0], -m[1], m[2], -m[3], m[4], -m[5], m[6], m[7], m[8]});
    }

    public float[] getAsFloatArray(){
        return this.m;
    }

    public Matrix3f getAsInversion(){

        if (this.det() == 0){
            System.out.println("Inverse doesn't exist! for: " + this.getAsString());
            return null;
        }

        float det_inv = 1 / this.det();

        float[] cofactor_mat = new float[] {
                this.getMinorDet(0), this.getMinorDet(3), this.getMinorDet(6),
                this.getMinorDet(1), this.getMinorDet(4), this.getMinorDet(7),
                this.getMinorDet(2), this.getMinorDet(5), this.getMinorDet(8),
        };

        cofactor_mat[1] *= -1;
        cofactor_mat[3] *= -1;
        cofactor_mat[5] *= -1;
        cofactor_mat[7] *= -1;

        for (int i = 0; i < 9; i++){
            cofactor_mat[i] *= det_inv;
        }

        return new Matrix3f(cofactor_mat);
    }

    public String getAsString(){
        return "\n" + m[0] + " " + m[1] + " " + m[2] + "\n" + m[3] + " " + m[4] + " " + m[5] + "\n" + m[6] + " " + m[7] + " " + m[8] + "\n";
    }
}
