package com.RTB.abhishekrawat.io;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

interface Reader {
		void read(File file, Consumer<BytesPayload> consumer) throws IOException;
}
