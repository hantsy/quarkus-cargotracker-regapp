package org.eclipse.cargotrakcer.regapp.client;

public sealed interface HandlingResponse permits
        HandlingResponse.Success,
        HandlingResponse.Failed {

    record Success() implements HandlingResponse {
        @Override
        public String toString() {
            return "OK";
        }
    }

    record Failed(String message) implements HandlingResponse {
    }
}
