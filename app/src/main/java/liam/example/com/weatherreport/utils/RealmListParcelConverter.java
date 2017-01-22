package liam.example.com.weatherreport.utils;

import android.os.Parcel;
import android.os.Parcelable;

import org.parceler.Parcels;
import org.parceler.TypeRangeParcelConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/*
 * Helper class used with parceller library to parcel realmLists.
 * @href https://gist.github.com/patloew/bc32a2a1a3c0097e9c7020192fb2c78f
 */
public class RealmListParcelConverter implements TypeRangeParcelConverter<RealmList<? extends RealmObject>, RealmList<? extends RealmObject>> {
    private static final int NULL = -1;

    @Override
    public void toParcel(RealmList<? extends RealmObject> input, Parcel parcel) {
        if (input == null) {
            parcel.writeInt(NULL);
        } else {
            parcel.writeInt(input.size());
            for (RealmObject item : input) {
                parcel.writeParcelable(Parcels.wrap(item), 0);
            }
        }
    }

    @Override
    public RealmList fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        RealmList list = new RealmList();

        for (int i = 0; i < size; i++) {
            Parcelable parcelable = parcel.readParcelable(getClass().getClassLoader());
            list.add((RealmObject) Parcels.unwrap(parcelable));
        }

        return list;
    }
}