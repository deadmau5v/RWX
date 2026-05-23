package android.os; public interface Parcelable { public interface Creator<T> {} void writeToParcel(Parcel dest, int flags); int describeContents(); }
