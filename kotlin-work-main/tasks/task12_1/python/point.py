# Task 12.1: Point class
import math

class Point:
    def __init__(self, x, y):
        self.attri_x = x
        self.attri_y = y

    def distance(self):
        return math.hypot(self.attri_x, self.attri_y)

    def distance_to(self, other):
        return math.hypot(other.attri_x - self.attri_x, other.attri_y - self.attri_y)

