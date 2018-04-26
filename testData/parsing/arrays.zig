const assert = @import("std").debug.assert;
const mem = @import("std").mem;

// array literal
// const message = []u8{'h', 'e', 'l', 'l', 'o'};

// get the size of an array
comptime {
	assert(message.len == 5);
}

// a string literal is an array literal
const same_message = "hello";

comptime {
	assert(mem.eql(u8, message, same_message));
	assert(@typeOf(message) == @typeOf(same_message));
}

test "iterate over an array" {
	var sum: usize = 0;
	for (message) |byte| {
		sum += byte;
	}
	assert(sum == usize('h') + usize('e') + usize('l') * 2 + usize('o'));
}

// modifiable array
var some_integers: [100]i32 = undefined;

test "modify an array" {
	for (some_integers) |*item, i| {
		*item = i32(i);
	}
	assert(some_integers[10] == 10);
	assert(some_integers[99] == 99);
}

// array concatenation works if the values are known
// at compile time
const part_one = []i32{1, 2, 3, 4};
const part_two = []i32{5, 6, 7, 8};
const all_of_it = part_one ++ part_two;
comptime {
	assert(mem.eql(i32, all_of_it, []i32{1,2,3,4,5,6,7,8}));
}

// remember that string literals are arrays
const hello = "hello";
const world = "world";
const hello_world = hello ++ " " ++ world;
comptime {
	assert(mem.eql(u8, hello_world, "hello world"));
}

// ** does repeating patterns
const pattern = "ab" ** 3;
comptime {
	assert(mem.eql(u8, pattern, "ababab"));
}

// initialize an array to zero
const all_zero = []u16{0} ** 10;

comptime {
	assert(all_zero.len == 10);
	assert(all_zero[5] == 0);
}

// use compile-time code to initialize an array
var fancy_array = init: {
	var initial_value: [10]Point = undefined;
	for (initial_value) |*pt, i| {
		*pt = Point {
			.x = i32(i),
			.y = i32(i) * 2,
		};
	}
	break :init initial_value;
};
const Point = struct {
	x: i32,
	y: i32,
};

test "compile-time array initalization" {
	assert(fancy_array[4].x == 4);
	assert(fancy_array[4].y == 8);
}

// call a function to initialize an array
var more_points = []Point{makePoint(3)} ** 10;
fn makePoint(x: i32) Point {
	return Point {
		.x = x,
		.y = x * 2,
	};
}

test "array initialization with function calls" {
	assert(more_points[4].x == 3);
	assert(more_points[4].y == 6);
	assert(more_points.len == 10);
}
