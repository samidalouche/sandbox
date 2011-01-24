class MyBehavior1(object):
    def calculate_something(self):
        return "1"
    
    def calculate_something_else(self):
        return "something"


class MyBehavior2(object):
    def calculate_something(self):
        return "2"

    def calculate_something_else(self):
        return "else"

MyBehavior = MyBehavior2


class BlaBla(object):
    def blibli(self):
        return "bli bli bli"
