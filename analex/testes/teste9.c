int main()
{
  int x = 1, y = 10;
  float a = 2, z = 1000;
  while (y > x)
  {
    y = y - 1;
    x = x * 2;
    z = z / x;
  }
  printf(x);
  printf(z);
  printf(x/(z - y));
}
