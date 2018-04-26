const std = @import("std");
const warn = std.debug.warn;

pub fn main() void {
    // integers
    const one_plus_one: i32 = 1 + 1;
    warn("1 + 1 = {}\n", one_plus<caret>_one);
}
