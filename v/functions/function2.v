fn main() {
	// println(divmod(9,2))

	a, b := divmod(9, 2)

	// can't print more than one values like this
	// use string interpolation
	// println(a, b)
	println(a)
	println(b)
	println(div(9, 2))

	// no errors so it returns +inf
	println(div(9, 0))
}

/*
multiple return types example
*/
fn divmod(a int, b int) (int, int) {
	return a / b, a % b
}

fn div(a f64, b f64) f64 {
	return a / b
}
