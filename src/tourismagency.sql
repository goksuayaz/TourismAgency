PGDMP  ,    .                |            tourismagency    16.2    16.2 $               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16923    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    16931    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id bigint NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_carpark boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_roomservice boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16977    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16943    pension    TABLE     m   CREATE TABLE public.pension (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_type text
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16979    pension_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16978    pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    16961    reservation    TABLE     �  CREATE TABLE public.reservation (
    id bigint NOT NULL,
    room_id bigint NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_count bigint NOT NULL,
    guest_name character varying(255) NOT NULL,
    guest_citizen_id character varying(255) NOT NULL,
    guest_phone character varying(255) NOT NULL,
    guest_mail character varying(255) NOT NULL,
    adult_count bigint NOT NULL,
    child_count bigint NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16987    reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_id_seq
    START WITH 3
    INCREMENT BY 1
    MINVALUE 3
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    220            �            1259    16970    room    TABLE     �  CREATE TABLE public.room (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    pension_id bigint NOT NULL,
    season_id bigint NOT NULL,
    type text NOT NULL,
    stock bigint NOT NULL,
    adult_price bigint NOT NULL,
    child_price bigint NOT NULL,
    bed_capacity bigint NOT NULL,
    square_meter bigint NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL,
    projection boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16980    room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    16952    season    TABLE     �   CREATE TABLE public.season (
    id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16985    season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_id_seq
    START WITH 4
    INCREMENT BY 1
    MINVALUE 4
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16924    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16938    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 4
    INCREMENT BY 1
    MINVALUE 4
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215                      0    16931    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_carpark, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_roomservice) FROM stdin;
    public          postgres    false    216   <+                 0    16943    pension 
   TABLE DATA           =   COPY public.pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    218   �+       
          0    16961    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, check_out_date, total_price, guest_count, guest_name, guest_citizen_id, guest_phone, guest_mail, adult_count, child_count) FROM stdin;
    public          postgres    false    220   E,                 0    16970    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    221   �,       	          0    16952    season 
   TABLE DATA           G   COPY public.season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    219   f-                 0    16924    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    215   �-                  0    0    hotel_hotel_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 10, true);
          public          postgres    false    222                       0    0    pension_hotel_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.pension_hotel_id_seq', 1, false);
          public          postgres    false    224                       0    0    pension_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.pension_id_seq', 1, false);
          public          postgres    false    223                       0    0    reservation_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.reservation_id_seq', 30, true);
          public          postgres    false    227                       0    0    room_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.room_id_seq', 21, true);
          public          postgres    false    225                       0    0    season_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.season_id_seq', 5, true);
          public          postgres    false    226                       0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 11, true);
          public          postgres    false    217            m           2606    16937    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    216            o           2606    16949    pension pension_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (id, hotel_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    218    218            s           2606    17046    reservation reservation_pkey 
   CONSTRAINT     u   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id, room_id, guest_citizen_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    220    220    220            u           2606    16976    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    221            q           2606    16958    season season_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (id, hotel_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    219    219            k           2606    16930    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215               �   x�m̿
�0���y�ҤĴ��N"N]��i0�A����v(Tn��>��Kkh�������&Ꞟ\�=b2v1)?(xNm�+�6(�;�K�K!��dA@Z�pV��=Fz@��=��9�{��+@7?�Ƹ���UU1��[�e��y���Tl$+�Zj2B���Q$         E   x�3�4�t��Q��S��s�2�4��		rT@4�4����
;�{��(��坃\]<C�b���� Vy      
   �   x�uν
1�z�����d�S{;K�h!r�rM���B�T�b؅��Ԉ_�����������Nl�f� �[�����֜S��0���!���E��m�s��P�K����O1�|�,�ܶ`���/�j�A�
)�v�}q�0�xZ@��9� �&E"         d   x�32�4C��Ҥ�T����\NCsNsNSa�ihj�Y�\@IC0D�a�iRl�aё�@���@h�����a�iV"L8-`v�t��qqq � M      	   4   x�3�4�4202�50"(���2�D�B�r�rCe�"�21z\\\ C��         W   x�3������4412�L�-�ɯLM�2�t��..�4162�LL����2�.I,�36B���.�5����qgWuAu��qqq ߖ4     