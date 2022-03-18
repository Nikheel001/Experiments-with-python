fn main() {
	print_star()
	println(add(5, 7))
}

/*
function name must be in camel case
otherwise it will throw error
*/
fn print_star() {
	println('*******************')
}

/*
no need for ';'
don't forget to add return type :D
*/
fn add(a int, b int) int {
	return a + b
}

/**
functions can't be overloaded
	/* conflicting declaration */
fn add(a int, b int) int {
	return a-b
}
*/
