const std = @import("std");
const warn = std.debug.warn;
const os = std.os;
const assert = std.debug.assert;

pub fn main() void {
    // integers
    const one_plus_one: i32 = 1 + 1;
    warn("1 + 1 = {}\n", one_plus_one);

    // floats
    const seven_div_three: f32 = 7.0 / 3.0;
    warn("7.0 / 3.0 = {}\n", seven_div_three);

    // boolean
    warn("{}\n{}\n{}\n",
        true and false,
        true or false,
        !true);

    // nullable
    var nullable_value: ?[]const u8 = null;
    assert(nullable_value == null);

    warn("\nnullable 1\ntype: {}\nvalue: {}\n",
        @typeName(@typeOf(nullable_value)), nullable_value);

    nullable_value = "hi";
    assert(nullable_value != null);

    warn("\nnullable 2\ntype: {}\nvalue: {}\n",
        @typeName(@typeOf(nullable_value)), nullable_value);

    // error union
    var number_or_error: error!i32 = error.ArgNotFound;

    warn("\nerror union 1\ntype: {}\nvalue: {}\n",
        @typeName(@typeOf(number_or_error)), number_or_error);

    number_or_error = 1234;

    warn("\nerror union 2\ntype: {}\nvalue: {}\n",
        @typeName(@typeOf(number_or_error)), number_or_error);
}
