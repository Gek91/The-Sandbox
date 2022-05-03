package service;

import com.smartsheet.api.SmartsheetException;

@FunctionalInterface
public interface SmartsheetBiFunction<T, U, R> {

	R apply(T t, U u) throws SmartsheetException;

}
