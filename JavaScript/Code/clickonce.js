function clickOnce() {
    if (this.hasOwnProperty('clicked')) {
        return;
    }
    console.log("You clicked!");
    this.clicked = true;
}

clickOnce();
clickOnce();
