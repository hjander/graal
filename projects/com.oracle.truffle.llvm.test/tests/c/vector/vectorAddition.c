typedef int V2SI __attribute__ ((vector_size (8)));

int main() {
	V2SI test1 = {-3, 4};
	V2SI test2 = {5, 8};
	V2SI result = test1 + test2;
	return result[0] + result[1];
}

