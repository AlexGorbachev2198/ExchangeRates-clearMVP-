package com.bpc.modulesdk.modulity.facilities;

//import ru.bpc.mobilebanksdk.dto.item.TransferTemplate;

/**
 * Created by Samoylov on 14.09.2016.
 */
@Deprecated
public interface TransfersList {

    /**
     * Get list of transfers, that application can process
     *
     * @return
     */
   // List<TransferUser> getListOfTransfers();

    /**
     * Find transfer that associated with {@code template}
     *
     * @param template template
     * @return null, if transfer not found
     */
   // @Nullable TransferUser find(TransferTemplate template);

    /**
     * Find transfer by tag
     * @param tag transfer tag
     * @return null if transfer not allowed
     */
   // @Nullable TransferUser find(String tag);

}
