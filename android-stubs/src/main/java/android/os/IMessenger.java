package android.os;

/* JADX INFO: loaded from: game-lib.jar:android/os/IMessenger.class */
public interface IMessenger extends IInterface {
    void a(Message message);

    /* JADX INFO: loaded from: game-lib.jar:android/os/IMessenger$Stub.class */
    public abstract class Stub extends Binder implements IMessenger {
        public Stub() {
             
        }

         // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

         // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            Message message;
            switch (i) {
                case 1:
                     
                    if (0 != parcel.readInt()) {
                        message = null;
                    } else {
                        message = null;
                    }
                    a(message);
                    break;
                case 1598968902:
                     
                    break;
            }
            return true;
        }
    }
}
