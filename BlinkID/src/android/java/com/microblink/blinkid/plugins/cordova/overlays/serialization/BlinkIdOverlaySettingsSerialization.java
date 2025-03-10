package com.microblink.blinkid.plugins.cordova.overlays.serialization;

import android.content.Context;

import com.microblink.blinkid.entities.recognizers.RecognizerBundle;
import com.microblink.blinkid.fragment.overlay.blinkid.reticleui.ReticleOverlayStrings;
import com.microblink.blinkid.uisettings.BlinkIdUISettings;
import com.microblink.blinkid.uisettings.UISettings;
import com.microblink.blinkid.plugins.cordova.overlays.OverlaySettingsSerialization;
import com.microblink.blinkid.plugins.cordova.SerializationUtils;
import com.microblink.blinkid.plugins.cordova.overlays.OverlaySerializationUtils;
import com.microblink.blinkid.locale.LanguageUtils;

import org.json.JSONObject;

import static com.microblink.blinkid.plugins.cordova.SerializationUtils.getStringFromJSONObject;

public final class BlinkIdOverlaySettingsSerialization implements OverlaySettingsSerialization {
    @Override
    public UISettings createUISettings(Context context, JSONObject jsonUISettings, RecognizerBundle recognizerBundle) {
        BlinkIdUISettings settings = new BlinkIdUISettings(recognizerBundle);

        OverlaySerializationUtils.prepareCommonUiSettings(context, jsonUISettings, settings);

        boolean requireDocumentSidesDataMatch = jsonUISettings.optBoolean("requireDocumentSidesDataMatch", true);
        settings.setDocumentDataMatchRequired(requireDocumentSidesDataMatch);

        boolean showNotSupportedDialog = jsonUISettings.optBoolean("showNotSupportedDialog", true);
        settings.setShowNotSupportedDialog(showNotSupportedDialog);

        boolean showFlashlightWarning = jsonUISettings.optBoolean("showFlashlightWarning", true);
        settings.setShowFlashlightWarning(showFlashlightWarning);

        long backSideScanningTimeoutMilliseconds = jsonUISettings.optLong("backSideScanningTimeoutMilliseconds", 17000);
        settings.setBackSideScanningTimeoutMs(backSideScanningTimeoutMilliseconds);

        ReticleOverlayStrings.Builder overlasStringsBuilder = new ReticleOverlayStrings.Builder(context);

        String firstSideInstructionsText = getStringFromJSONObject(jsonUISettings, "firstSideInstructionsText");
        if (firstSideInstructionsText != null) {
            overlasStringsBuilder.setFirstSideInstructionsText(firstSideInstructionsText);
        }
        String flipInstructions = getStringFromJSONObject(jsonUISettings, "flipInstructions");
        if (flipInstructions != null) {
            overlasStringsBuilder.setFlipInstructions(flipInstructions);
        }
        String errorMoveCloser = getStringFromJSONObject(jsonUISettings, "errorMoveCloser");
        if (errorMoveCloser != null) {
            overlasStringsBuilder.setErrorMoveCloser(errorMoveCloser);
        }
        String errorMoveFarther = getStringFromJSONObject(jsonUISettings, "errorMoveFarther");
        if (errorMoveFarther != null) {
            overlasStringsBuilder.setErrorMoveFarther(errorMoveFarther);
        }
        String sidesNotMatchingTitle = getStringFromJSONObject(jsonUISettings, "sidesNotMatchingTitle");
        if (sidesNotMatchingTitle != null) {
            overlasStringsBuilder.setSidesNotMatchingTitle(sidesNotMatchingTitle);
        }
        String sidesNotMatchingMessage = getStringFromJSONObject(jsonUISettings, "sidesNotMatchingMessage");
        if (sidesNotMatchingMessage != null) {
            overlasStringsBuilder.setSidesNotMatchingMessage(sidesNotMatchingMessage);
        }
        String unsupportedDocumentTitle = getStringFromJSONObject(jsonUISettings, "unsupportedDocumentTitle");
        if (unsupportedDocumentTitle != null) {
            overlasStringsBuilder.setUnsupportedDocumentTitle(unsupportedDocumentTitle);
        }
        String unsupportedDocumentMessage = getStringFromJSONObject(jsonUISettings, "unsupportedDocumentMessage");
        if (unsupportedDocumentMessage != null) {
            overlasStringsBuilder.setUnsupportedDocumentMessage(unsupportedDocumentMessage);
        }
        String recognitionTimeoutTitle = getStringFromJSONObject(jsonUISettings, "recognitionTimeoutTitle");
        if (recognitionTimeoutTitle != null) {
            overlasStringsBuilder.setRecognitionTimeoutTitle(recognitionTimeoutTitle);
        }
        String recognitionTimeoutMessage = getStringFromJSONObject(jsonUISettings, "recognitionTimeoutMessage");
        if (recognitionTimeoutMessage != null) {
            overlasStringsBuilder.setRecognitionTimeoutMessage(recognitionTimeoutMessage);
        }
        String retryButtonText = getStringFromJSONObject(jsonUISettings, "retryButtonText");
        if (retryButtonText != null) {
            overlasStringsBuilder.setRetryButtonText(retryButtonText);
        }
        String scanBarcodeText = getStringFromJSONObject(jsonUISettings, "scanBarcodeText");
        if (scanBarcodeText != null) {
            overlasStringsBuilder.setBackSideBarcodeInstructions(scanBarcodeText);
        }
        String errorDocumentTooCloseToEdge = getStringFromJSONObject(jsonUISettings, "errorDocumentTooCloseToEdge");
        if (errorDocumentTooCloseToEdge != null) {
            overlasStringsBuilder.setErrorDocumentTooCloseToEdge(errorDocumentTooCloseToEdge);
        }

        String language = getStringFromJSONObject(jsonUISettings, "language");
        if (language != null) {
            String country = getStringFromJSONObject(jsonUISettings, "country");
            if (country != null) {
                LanguageUtils.setLanguageAndCountry(language, country, context);
            }
        }

        settings.setStrings(overlasStringsBuilder.build());

        return settings;
    }

    @Override
    public String getJsonName() {
        return "BlinkIdOverlaySettings";
    }
}