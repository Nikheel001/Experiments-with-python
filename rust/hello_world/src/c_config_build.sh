#[without cargo]
rustc -Clinker=clang -Clink-arg=-fuse-ld=mold main.rs

#[verify]
readelf -p .comment main

#[with cargo]
# add .cargo/config.toml to project_home or global .cargo

# either build for specific target or add to all
# example : 
# cargo build --release --target aarch64-unknown-linux-gnu
# cat config.toml
# [target.aarch64-unknown-linux-gnu]
# linker = "clang"
# rustflags = ["-C", "link-arg=-fuse-ld=mold"]

#[verify]
readelf -p .comment target/release/hello_world
readelf -p .comment target/aarch64-unknown-linux-gnu/release/hello_world

