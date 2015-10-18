package ee.lps.util;

/**
 * Wraps primitives. Useful when returning primitives in controllers as json.
 */
public class Wrapper {
    public static <T> WrappedObj<T> wrap(T value) {
        return new WrappedObj<>(value);
    }

    public static class WrappedObj<T> {
        private T value;

        public WrappedObj(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}