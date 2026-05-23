package android.graphics;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: game-lib.jar:android/graphics/Point.class */
public class Point implements Parcelable {

    /* JADX INFO: renamed from: a */
    public int worldX;
    public int x; // alias for worldX

    /* JADX INFO: renamed from: b */
    public int worldY;
    public int y; // alias for worldY
    public static final Parcelable.Creator c = new Parcelable.Creator() { // from class: android.graphics.Point.1
         // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Point createFromParcel(Parcel parcel) {
            Point point = new Point();
            point.a(parcel);
            return point;
        }

         // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Point[] newArray(int i) {
            return new Point[i];
        }
    };

    public Point() {
    }

    public Point(int i, int i2) {
        this.worldX = i;
        this.worldY = i2;
    }

    public void set(int i, int i2) { a(i, i2); }
    public void a(int i, int i2) {
        this.worldX = i;
        this.worldY = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return this.worldX == point.worldX && this.worldY == point.worldY;
    }

    public int hashCode() {
        return (31 * this.worldX) + this.worldY;
    }

    public String toString() {
        return "Point(" + this.worldX + ", " + this.worldY + ")";
    }

     // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

     // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.worldX);
        parcel.writeInt(this.worldY);
    }

    public void a(Parcel parcel) {
        this.worldX = parcel.readInt();
        this.worldY = parcel.readInt();
    }
}
